package barmin.twino.loans.api.dto

data class ScoreDTO(
    val loanId: Long,
    val status: LoanStatus
)