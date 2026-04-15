import express from 'express';
import { authenticateToken, requireRole } from '../auth.js';
import { query } from '../db.js';

const router = express.Router();

function normalizeNoticeRow(row) {
  if (!row) {
    return null;
  }

  return {
    audience: row.audience,
    deadline: row.deadline === null ? null : Number(row.deadline),
    updatedAt: row.updated_at === null ? null : Number(row.updated_at),
    active: row.deadline !== null
  };
}

router.get('/notices/registration', async (req, res) => {
  try {
    const result = await query(
      'SELECT audience, deadline, updated_at FROM registration_notices WHERE audience IN ($1, $2)',
      ['candidate', 'voter']
    );

    const noticesByAudience = result.rows.reduce((acc, row) => {
      acc[row.audience] = normalizeNoticeRow(row);
      return acc;
    }, {});

    return res.json({
      candidateNotice: noticesByAudience.candidate ?? null,
      voterNotice: noticesByAudience.voter ?? null
    });
  } catch (error) {
    console.error('Error loading registration notices:', error);
    return res.status(500).json({ error: 'Failed to load registration notices' });
  }
});

router.put('/notices/registration/:audience', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const audience = `${req.params.audience || ''}`.trim().toLowerCase();
    if (audience !== 'candidate' && audience !== 'voter') {
      return res.status(400).json({ error: "Audience must be 'candidate' or 'voter'" });
    }

    const hasDeadlineField = Object.prototype.hasOwnProperty.call(req.body ?? {}, 'deadline');
    const deadline = hasDeadlineField ? req.body.deadline : undefined;

    if (deadline === null) {
      await query('DELETE FROM registration_notices WHERE audience = $1', [audience]);
      return res.json({
        audience,
        deadline: null,
        updatedAt: null,
        active: false
      });
    }

    if (deadline === undefined || deadline === '') {
      return res.status(400).json({ error: 'Deadline is required unless you are clearing the notice' });
    }

    const numericDeadline = Number(deadline);
    if (!Number.isFinite(numericDeadline) || numericDeadline <= 0) {
      return res.status(400).json({ error: 'Deadline must be a valid timestamp' });
    }

    const now = Date.now();
    const result = await query(
      `INSERT INTO registration_notices (audience, deadline, updated_at)
       VALUES ($1, $2, $3)
       ON CONFLICT (audience)
       DO UPDATE SET deadline = EXCLUDED.deadline, updated_at = EXCLUDED.updated_at
       RETURNING audience, deadline, updated_at`,
      [audience, numericDeadline, now]
    );

    return res.json(normalizeNoticeRow(result.rows[0]));
  } catch (error) {
    console.error('Error updating registration notice:', error);
    return res.status(500).json({ error: 'Failed to update registration notice' });
  }
});

export default router;
