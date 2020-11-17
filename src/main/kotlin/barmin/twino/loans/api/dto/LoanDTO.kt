package barmin.twino.loans.api.dto

import java.math.BigDecimal
import java.time.LocalDate

data class LoanDTO(
    val personalId: Long,

    val firstName: String,

    val lastName: String?,

    val birthDate: LocalDate,

    val employer: String?,

    val salary: BigDecimal = BigDecimal.ZERO,

    val liability: BigDecimal = BigDecimal.ZERO,

    val requestedAmount: BigDecimal = BigDecimal.ZERO,

    val requestedTerm: Long = 0,

    val status: LoanStatus? = null
)