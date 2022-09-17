package dddfaq.domain.screening

import java.time.LocalDateTime
import java.util.UUID

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

class DomainException(message: String) : Throwable(message)

enum class ScreeningStatus(
    val canAddInterview: Boolean
) {
    IN_PROGRESS(true),
    ADDOPTED(false),
    REJECTED(false),
}

data class Interview(
    val phase: Int,
    val dateTime: LocalDateTime
)

data class Interviews(val value: List<Interview>) {

    fun addInterview(interviewDateTime: LocalDateTime): Interviews {
        val newInterview = Interview(
            phase = this.value.size + 1,
            dateTime = interviewDateTime
        )
        return Interviews(this.value + newInterview)
    }

    companion object {
        fun empty() = Interviews(emptyList())
    }
}

data class Applicant(val name: String, val mailAddress: String)

data class PositionId(val value: String) {
    constructor() : this(UUID.randomUUID().toString())
}

data class ScreeningId(val value: String) {
    constructor() : this(UUID.randomUUID().toString())
}
