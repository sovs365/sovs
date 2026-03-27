package com.university.voting.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.university.voting.databinding.ActivityTermsConditionsBinding

class TermsConditionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTermsConditionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }
        
        binding.tvTerms.text = """
Terms and Conditions for Secure Online Voting System (SOVS)
1. Acceptance of Terms
By registering for, accessing, or using the Student Online Voting System (SOVS), you agree to be bound by these Terms and Conditions. If you do not agree with any part of these terms, you should not use the application.
2. Purpose of the Application
SOVS is designed to provide a secure and efficient platform for conducting student elections within the institution. The system allows eligible users to register, log in, view elections, cast votes, and access election-related information according to their assigned role.
3. Eligibility
To use this application, you must:
•	Be a registered student or authorized user of the institution.
•	Provide correct, complete, and truthful information during registration.
•	Use only your own credentials and institutional details.
•	Be approved by the system where necessary.
Any false, misleading, or incomplete information may lead to suspension or termination of access.
4. User Account Responsibility
Each user is responsible for maintaining the confidentiality of their login credentials, including username, registration number, email, and password. You agree:
•	Not to share your account with another person.
•	Not to allow another person to vote on your behalf.
•	To notify the system administrator immediately if you suspect unauthorized access to your account.
The institution and system administrators shall not be responsible for losses arising from negligence in protecting login credentials.
5. One Person, One Vote
Each eligible voter is allowed to vote only once per election or per position as defined by the system rules. Any attempt to:
•	Vote more than once,
•	Use multiple accounts,
•	Interfere with another user’s vote,
•	Manipulate the voting process,
shall be considered a serious violation and may result in disqualification, account suspension, disciplinary action, or legal action under institutional policies.
6. Accuracy of Information
Users must ensure that all details submitted during registration, candidature application, or profile updates are accurate and up to date. This includes:
•	Full name
•	Registration number
•	Faculty
•	Course
•	Year of study
•	Level of study
•	Phone number
•	Email address
•	Any required supporting documents
Submission of false information may lead to rejection of candidature, account deactivation, or cancellation of votes.
7. Candidate Obligations
Any user applying as a candidate agrees to:
•	Submit genuine and valid documents as required by the institution.
•	Provide a truthful manifesto and profile details.
•	Follow all election rules and code of conduct set by the institution.
•	Avoid using the platform for abusive, misleading, hateful, or unlawful campaign content.
The administrator reserves the right to review, approve, reject, or remove candidate applications that do not meet institutional requirements.
8. Proper Use of the System
Users agree to use the application only for lawful and authorized purposes. You must not:
•	Attempt to hack, modify, disrupt, or overload the system.
•	Upload harmful files, malicious code, or viruses.
•	Access data or accounts that do not belong to you.
•	Use offensive, abusive, threatening, or discriminatory language.
•	Engage in any activity that undermines the fairness or integrity of the election.
Any such misuse may result in immediate suspension and disciplinary measures.
9. Privacy and Data Protection
By using SOVS, you consent to the collection and processing of your data for election-related purposes. The system may collect and store:
•	Personal identification details
•	Academic details
•	Login credentials in protected form
•	Voting records in secure form
•	Uploaded candidate documents
Your information will be used only for lawful academic and electoral purposes, system management, authentication, verification, and result processing. Reasonable technical and administrative measures shall be taken to protect your information from unauthorized access, alteration, or disclosure.
10. Confidentiality of Voting
The system is designed to preserve the secrecy and confidentiality of votes. Users must not attempt to expose, trace, alter, or tamper with voting records. Any attempt to compromise vote secrecy or election integrity will be treated as a serious offense.
11. System Availability
Although efforts will be made to ensure continuous access, the institution does not guarantee that the application will always be available without interruption. Access may be affected by:
•	Internet failure
•	Server downtime
•	Maintenance activities
•	Security upgrades
•	Technical faults beyond reasonable control
The institution reserves the right to temporarily suspend the system for maintenance, security reasons, or administrative purposes.
12. Verification and Approval
The institution or system administrator may verify user details and documents before granting full access to certain services. Candidate applications, uploaded files, and user records may be reviewed for authenticity and compliance with election requirements.
13. Administrative Rights
System administrators have the right to:
•	Approve or reject registrations and candidate applications
•	Correct or update election records where necessary
•	Suspend accounts involved in suspicious or prohibited activities
•	Manage elections, candidates, positions, and results
•	Investigate system misuse or security incidents
These actions shall only be taken for legitimate academic, administrative, or security reasons.
14. Intellectual Property
The application design, source code, system structure, content, logos, and related materials remain the property of the developer and/or institution unless otherwise stated. Users may not copy, modify, distribute, or reproduce any part of the system without authorization.
15. Limitation of Liability
The institution, developer, and system administrators shall not be held liable for:
•	Loss resulting from incorrect information submitted by users
•	Account misuse caused by shared passwords
•	Delays caused by internet or technical failures
•	Disruptions beyond reasonable control
•	User negligence or violation of these terms
However, reasonable efforts shall be made to maintain fairness, security, and reliability.
16. Suspension or Termination of Access
A user’s access may be suspended or terminated if they:
•	Provide false registration details
•	Attempt to vote fraudulently
•	Misuse the application
•	Violate these Terms and Conditions
•	Breach institutional election rules
Where necessary, such cases may also be reported to relevant disciplinary authorities within the institution.
17. Amendments to Terms
The institution reserves the right to revise or update these Terms and Conditions at any time to reflect changes in law, policy, technology, or election procedures. Continued use of the system after such updates means that the user accepts the revised terms.
18. Governing Rules
These Terms and Conditions shall be governed by the policies, rules, and regulations of the institution, together with any applicable laws governing data protection, digital systems, and electoral conduct.
19. Contact and Support
For any issue concerning registration, login, voting, candidature, or technical support, users should contact the system administrator or the designated election management office.
20. Declaration
By checking the box labeled “I agree to the Terms and Conditions”, you confirm that:
•	You have read and understood these Terms and Conditions.
•	The information you have provided is true and correct.
•	You agree to use the system responsibly and lawfully.
•	You understand that violation of these terms may lead to suspension, disqualification, or disciplinary action.
        """.trimIndent()
    }
}
