package dddfaq.domain.screening

enum class ScreeningStatus(
  val canAddInterview: Boolean
) {
  IN_PROGRESS(true),
  ADDOPTED(false),
  REJECTED(false),
}