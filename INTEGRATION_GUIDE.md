# Backend Integration Guide - Connecting Your Frontend

This guide explains how to connect your Android/web frontend to the deployed Node.js/Express backend.

## Backend URLs

### Development
```
http://localhost:8080
```

### Production (Vercel)
```
https://university-voting-backend-[YOUR-PROJECT].vercel.app
```

Replace `[YOUR-PROJECT]` with your actual Vercel project name.

## API Base URL Configuration

### Android (Kotlin)

```kotlin
// In your retrofit/okhttp client configuration
object ApiClient {
    private const val BASE_URL = "https://university-voting-backend-YOURPROJECT.vercel.app/"
    
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(ApiService::class.java)
    }
}
```

### Web (JavaScript/React)

```javascript
// In your API configuration file
const API_BASE_URL = 'https://university-voting-backend-YOURPROJECT.vercel.app';

export const apiClient = {
  async request(endpoint, options = {}) {
    const response = await fetch(`${API_BASE_URL}${endpoint}`, {
      headers: {
        'Content-Type': 'application/json',
        ...options.headers,
      },
      ...options,
    });
    
    if (!response.ok) {
      throw new Error(`API Error: ${response.status}`);
    }
    
    return response.json();
  },

  async get(endpoint) {
    return this.request(endpoint, { method: 'GET' });
  },

  async post(endpoint, body) {
    return this.request(endpoint, {
      method: 'POST',
      body: JSON.stringify(body),
    });
  },

  async patch(endpoint, body) {
    return this.request(endpoint, {
      method: 'PATCH',
      body: JSON.stringify(body),
    });
  },
};
```

### Flutter

```dart
import 'package:http/http.dart' as http;

class ApiService {
  static const String _baseUrl = 'https://university-voting-backend-YOURPROJECT.vercel.app';
  
  Future<Map<String, dynamic>> post(String endpoint, Map<String, dynamic> body) async {
    final response = await http.post(
      Uri.parse('$_baseUrl$endpoint'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(body),
    );
    
    if (response.statusCode != 200 && response.statusCode != 201) {
      throw Exception('API Error: ${response.statusCode}');
    }
    
    return jsonDecode(response.body);
  }
}
```

## Authentication Flow

### 1. User Registration

**Request:**
```bash
POST /api/auth/register
Content-Type: application/json

{
  "username": "student001",
  "password": "SecurePassword123",
  "fullName": "John Doe",
  "email": "john@university.edu",
  "phoneNumber": "254712345678",
  "role": "voter",
  "regNo": "REG/2024/001",
  "faculty": "Engineering",
  "course": "Computer Science",
  "yearOfStudy": "2"
}
```

**Response (Success - 201):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "userId": "123e4567-e89b-12d3-a456-426614174000",
    "username": "student001",
    "fullName": "John Doe",
    "email": "john@university.edu",
    "role": "voter",
    "isVerified": true,
    "phoneNumber": "254712345678"
  }
}
```

**Response (Error - 400):**
```json
{
  "error": "Username already exists"
}
```

### 2. User Login

**Request:**
```bash
POST /api/auth/login
Content-Type: application/json

{
  "username": "student001",
  "password": "SecurePassword123"
}
```

**Response (Success - 200):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "userId": "123e4567-e89b-12d3-a456-426614174000",
    "username": "student001",
    "fullName": "John Doe",
    "role": "voter",
    "isVerified": true
  }
}
```

### 3. Store Token (Client-Side)

**Android (Kotlin) - SharedPreferences:**
```kotlin
val sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
sharedPref.edit().putString("token", response.token).apply()
sharedPref.edit().putString("userId", response.user.userId).apply()
```

**Web (JavaScript) - LocalStorage:**
```javascript
localStorage.setItem('token', response.token);
localStorage.setItem('userId', response.user.userId);
localStorage.setItem('userRole', response.user.role);
```

**Flutter - SharedPreferences:**
```dart
final prefs = await SharedPreferences.getInstance();
await prefs.setString('token', response['token']);
await prefs.setString('userId', response['user']['userId']);
```

### 4. Use Token in Requests

**Add to Request Headers:**
```
Authorization: Bearer {TOKEN}
```

**JavaScript Example:**
```javascript
const token = localStorage.getItem('token');

fetch(`${API_BASE_URL}/api/auth/me`, {
  headers: {
    'Authorization': `Bearer ${token}`,
    'Content-Type': 'application/json'
  }
});
```

**Android Example:**
```kotlin
val token = sharedPref.getString("token", "")
request.addHeader("Authorization", "Bearer $token")
```

## Common API Patterns

### Authenticated GET Request

```javascript
// Fetch current user
const response = await fetch(
  `${API_BASE_URL}/api/auth/me`,
  {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    }
  }
);
```

### Authenticated POST Request

```javascript
// Cast a vote
const response = await fetch(
  `${API_BASE_URL}/api/votes`,
  {
    method: 'POST',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      electionId: 'election-123',
      votes: [
        {
          positionId: 'position-456',
          candidateId: 'candidate-789'
        }
      ]
    })
  }
);
```

### Handle Errors

```javascript
try {
  const response = await fetch(endpoint, options);
  
  if (response.status === 401) {
    // Token expired, redirect to login
    localStorage.removeItem('token');
    window.location.href = '/login';
  } else if (response.status === 403) {
    // Insufficient permissions
    console.error('Permission denied');
  } else if (!response.ok) {
    const error = await response.json();
    console.error('API Error:', error.error);
  }
  
  return await response.json();
} catch (error) {
  console.error('Network error:', error);
}
```

## Complete Endpoint Reference

### Authentication
```
POST   /api/auth/register              - Register new user
POST   /api/auth/login                 - User login
GET    /api/auth/me                    - Get current user (requires auth)
POST   /api/auth/send-reset-code       - Send password reset code
POST   /api/auth/verify-reset-code     - Verify reset code
POST   /api/auth/reset-password        - Reset password
```

### Elections
```
GET    /api/elections                  - List all elections
GET    /api/elections/:id              - Get election details
POST   /api/elections                  - Create election (admin)
PATCH  /api/elections/:id/status       - Update election status (admin)
```

### Positions
```
GET    /api/positions                  - List all positions
GET    /api/positions/:id              - Get position details
POST   /api/positions                  - Create position (admin)
```

### Candidates
```
GET    /api/candidates                 - List all candidates
GET    /api/candidates/:id             - Get candidate details
GET    /api/candidates/position/:id    - Get candidates for position
POST   /api/candidates                 - Register as candidate (requires auth)
PATCH  /api/candidates/:id/verify      - Verify candidate (admin)
```

### Voting
```
POST   /api/votes                      - Cast vote (requires auth)
GET    /api/votes/results/:electionId  - Get election results
```

### Data (Universities/Courses)
```
GET    /api/data/faculties             - List all faculties
GET    /api/data/courses               - List courses (optional: ?facultyId=X)
GET    /api/data/subject-combinations  - List subject combos (optional: ?courseId=X)
```

### Admin Only
```
GET    /api/admin/users                - List all users
GET    /api/admin/users/role/:role     - List users by role
PATCH  /api/admin/users/:id/verify     - Verify/unverify user
PATCH  /api/admin/users/:id/lock       - Lock/unlock account
POST   /api/admin/faculties            - Create faculty
POST   /api/admin/courses              - Create course
POST   /api/admin/subject-combinations - Create subject combination
GET    /api/admin/reports              - Get statistics
GET    /api/admin/elections/:id/details - Get election breakdown
POST   /api/admin/logs                 - Log admin action
```

### Health Checks
```
GET    /health                         - Liveness probe
GET    /health/ready                   - Readiness probe (includes DB check)
```

## Error Responses

All errors follow this format:

```json
{
  "error": "Human readable error message"
}
```

### Common HTTP Status Codes

| Code | Meaning | Action |
|------|---------|--------|
| 200 | OK | Request succeeded |
| 201 | Created | Resource created |
| 400 | Bad Request | Check request data |
| 401 | Unauthorized | Add auth token |
| 403 | Forbidden | User lacks permissions |
| 404 | Not Found | Resource doesn't exist |
| 500 | Server Error | Check logs |
| 503 | Unavailable | Database connection failed |

## Testing Durante Development

### Use Postman or curl for testing:

```bash
# Register user
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "TestPass123",
    "fullName": "Test User",
    "phoneNumber": "2547XXXXXXXX",
    "role": "voter",
    "regNo": "TEST001",
    "faculty": "Engineering",
    "course": "CS"
  }'

# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "TestPass123"
  }'

# Get current user (replace TOKEN)
curl -X GET http://localhost:8080/api/auth/me \
  -H "Authorization: Bearer TOKEN"
```

## Debugging Tips

### Enable Verbose Logging

**Frontend JavaScript:**
```javascript
// Log all API calls
fetch = (function(originalFetch) {
  return function(...args) {
    console.log('API Call:', args[0], args[1]);
    return originalFetch.apply(this, args)
      .then(res => {
        console.log('API Response:', res.status);
        return res;
      });
  };
})(fetch);
```

### Check Network Tab

- Use browser DevTools Network tab (F12)
- Look for request/response headers
- Verify Authorization header is present
- Check response status and body

### Verify CORS

If you see CORS errors, check that:
1. Backend has `CORS_ALLOWED_HOSTS` set to your frontend URL
2. Frontend URL includes protocol (https://)
3. No trailing slashes in URLs

## Compatibility Notes

- Backend is 100% compatible with original Kotlin backend
- All endpoints return the same data format
- All validations are identical
- Auto-verification logic works the same way
- Admin features are equivalent

## Performance Tips

1. **Implement Request Caching**
   - Cache list endpoints (elections, positions)
   - Invalidate on mutations

2. **Use Token Refresh**
   - Implement refresh token flow
   - Keep session alive longer

3. **Batch Requests**
   - Combine multiple reads when possible
   - Reduce number of API calls

4. **Error Recovery**
   - Implement exponential backoff for retries
   - Graceful degradation

## Security Reminders

- ✅ Never log tokens to console in production
- ✅ Always use HTTPS in production
- ✅ Validate all user inputs on frontend
- ✅ Never store sensitive data (passwords) locally
- ✅ Implement request timeouts
- ✅ Clear tokens on logout

## Support & Next Steps

1. Test all endpoints with Postman/curl
2. Integrate into your frontend app
3. Deploy frontend to Vercel/Netlify
4. Monitor both backend and frontend logs
5. Set up error tracking (Sentry, LogRocket)

---

**Your backend is ready! Start integrating your frontend now!**
