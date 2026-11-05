# AiShieldMe - Program Flow Architecture

## 1. High-Level Architecture Overview

The app follows modern Android Architecture guidelines with **Unidirectional Data Flow (UDF)**:
* **UI Layer**: Jetpack Compose Screens & ViewModels (`StateFlow`, `SharedFlow`).
* **Navigation Layer**: Jetpack Compose Navigation (`AppNavHost`) with type-safe `Destination` routes.
* **Domain & Data Layer**: Repositories managing remote API requests via Retrofit/OkHttp and local storage via Room & DataStore (`SessionStore`).
* **Security & AI Layer**: Vault PIN / Biometric gate (`VaultGateViewModel`), ML Kit document scanner, and On-Device Generative AI (`CheckInAiRepository`).

---

## 2. Program Flow Diagram

```
                             ┌───────────────────────┐
                             │     App Startup       │                             │    (SplashScreen)     │
                             └───────────┬───────────┘
                                         │
                   ┌─────────────────────┴─────────────────────┐
                   ▼                                           ▼
         [No Valid Session]                             [Valid Session]
                   │                                           │
         ┌─────────┴─────────┐                       ┌─────────┴─────────┐
         │  OnboardingRoute  │                       │  Check Vault PIN  │
         └─────────┬─────────┘                       └─────────┬─────────┘
                   │                                           │
         ┌─────────▼─────────┐                      ┌──────────┴──────────┐
         │    LoginRoute     │                      ▼                     ▼
         └────┬──────────┬───┘                [PIN Required]         [PIN Configured]
              │          │                          │                     │
       (Sign Up)    (Forgot Password)     ┌─────────▼─────────┐  ┌────────▼─────────┐
              │          │                │ CreateVaultPin    │  │ DashboardRoute   │
              ▼          ▼                └─────────┬─────────┘  └────────┬─────────┘
      ┌───────────────┐ ┌───────────────┐           │                     │
      │ SignupRoute   │ │ ResetPassword │           └──────────┬──────────┘
      └───────┬───────┘ └───────────────┘                      │
              │                                                │
      ┌───────▼───────┐                                        │
      │ BasicInfoRoute│                                        │
      └───────┬───────┘                                        │
              │                                                │
      ┌───────▼───────┐                                        │
      │VerifyEmail(OTP)                                        │
      └───────┬───────┘                                        │
              │                                                │
      ┌───────▼───────┐                                        │
      │ SetupProfile  │                                        │
      └───────┬───────┘                                        │
              │                                                │
              └────────────────────────────────────────────────┘
                                      │
                                      ▼
                        ┌───────────────────────────┐
                        │     DASHBOARD HUB         │
                        └─────────────┬─────────────┘
                                      │
         ┌────────────────────────────┼────────────────────────────┐
         │                            │                            │
         ▼                            ▼                            ▼
┌──────────────────┐        ┌──────────────────┐         ┌──────────────────┐
│   Folder / Docs  │        │   Industries     │         │ Profile / Vault  │
│  (Vault Protected│        │  Services        │         │   Settings       │
└────────┬─────────┘        └────────┬─────────┘         └────────┬─────────┘
         │                           │                            │
   [Vault Gate]          ┌───────────┼───────────┐                ├─ Edit Profile
   (PIN/Biometric)       │           │           │                ├─ Reset Vault PIN
         │               ▼           ▼           ▼                ├─ Availability
         ▼          Healthcare    Retail      Banking             └─ Static Pages
   Documents /      (Check-In AI) (Orders)   (Statements)
   Sharing Flow
```

---

## 3. Detailed Step-by-Step Flow Description

### A. Startup & Session Check
1. **`SplashRoute`**: Checks stored user tokens and vault pin state in `SessionStore`.
   - **First Launch**: Redirects to `OnboardingRoute`.
   - **No Session**: Redirects to `LoginRoute`.
   - **Session Active but PIN missing**: Redirects to `CreateVaultPinRoute`.
   - **Session Active**: Navigates directly to `DashboardRoute`.

### B. Authentication & Onboarding Flow
1. **`OnboardingRoute`**: Swiping intro screens -> proceeds to `LoginRoute`.
2. **`LoginRoute`**:
   - Authenticates via `AuthRepository`.
   - Requires email OTP verification if needed -> `VerifyEmailRoute`.
   - Directs new registrations -> `SignupRoute` -> `BasicInfoRoute` -> `VerifyEmailRoute` -> `SetupProfileRoute` -> `CreateVaultPinRoute`.
3. **`ResetPasswordRoute`**: Handles password resets via email token or direct password changes.

### C. Main Navigation & Vault Protection
1. **`DashboardRoute`**: The main entry point featuring folder categories, industry portals, and quick user actions.
2. **Vault Security Gate (`VaultGateDialog`)**:
   - Accessing sensitive folders (`FoldersRoute`, `DocumentsRoute`) triggers the `VaultGateViewModel`.
   - Prompts for **Biometric Authentication** or **Vault PIN Entry**.
   - Upon successful unlock, navigates to `DocumentsRoute` or `ShareRoute`.

### D. Industry Flow Breakdown
1. **Healthcare Portal (`HealthcareRoute`)**:
   - **Appointments**: View details via `AppointmentDetailRoute` (uses `DetailHandoff`).
   - **Interactive Patient Check-In**:
     1. **`CheckInFormRoute`**: Multistep AI-guided form entry (symptoms, history, ML Kit document scanner for insurance/ID).
     2. **`CheckInPreviewRoute`**: Form review and digital signature capture (`SignaturePad`).
     3. **`CheckInReportRoute`**: Generates patient report via `CheckInAiRepository` (On-Device AI), renders PDF export (`CheckInPdf`), and saves history locally to Room DB (`CheckInHistoryDao`).
2. **Retail Portal (`RetailRoute`)**:
   - Browse retail orders -> View details via `RetailDetailRoute`.
3. **Banking Portal (`BankingRoute`)**:
   - View bank accounts & statements -> Inspect details & open PDF statements via `BankStatementDetailRoute` (`FileOpener`).

### E. Security & Data Management
- **Network Interceptor (`Interceptors.kt`)**: Appends Bearer Auth headers; monitors responses for expired tokens (`401 Un-authorized`) and triggers `appViewModel.sessionEnded` to clear session and return to `LoginRoute`.
- **Local Vault (`VaultLock.kt`)**: Locks sensitive state after timeout or app backgrounding.
