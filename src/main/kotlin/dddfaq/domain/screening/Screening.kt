package dddfaq.domain.screening

import java.time.LocalDateTime

class Screening private constructor(
  val screeningId: ScreeningId,
  val positionId: PositionId,
  val applicant: Applicant,
  val applyDateTime: LocalDateTime,
  private var interviews: Interviews,
  private var status: ScreeningStatus,
) {
  companion object {
    fun create(positionId: PositionId, applicant: Applicant): Screening {
      return Screening(
        screeningId = ScreeningId(),
        positionId = positionId,
        applicant = applicant,
        interviews = Interviews.empty(),
        status = ScreeningStatus.IN_PROGRESS,
        applyDateTime = LocalDateTime.now(),
      )
    }

    fun reconstruct(
      screeningId: ScreeningId,
      positionId: PositionId,
      applicant: Applicant,
      interviews: Interviews,
      status: ScreeningStatus,
      applyDateTime: LocalDateTime,
    ): Screening {
      return Screening(
        screeningId = screeningId,
        positionId = positionId,
        applicant = applicant,
        interviews = interviews,
        status = status,
        applyDateTime = applyDateTime,
      )
    }
  }

  fun addInterview(interviewDateTime: LocalDateTime) {
    if (!this.status.canAddInterview) {
      throw DomainException("The interview status isn't IN_PROGRESS.")
    }
    this.interviews = this.interviews.addInterview(interviewDateTime)
  }

  fun adopt() {
    this.status = ScreeningStatus.ADDOPTED
  }

  fun reject() {
    this.status = ScreeningStatus.REJECTED
  }
}
