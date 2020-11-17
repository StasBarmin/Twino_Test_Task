package barmin.twino.loans.api.dto

import java.math.BigDecimal
import java.time.LocalDate
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Pattern
import javax.validation.constraints.PositiveOrZero

data class LoanCreateRequest(
    @get:Pattern(regexp = "^\\w+$")
    var firstName: String = " ",

    var lastName: String?,

    var birthDate: LocalDate = LocalDate.now(),

    var employer: String?,

    @get:DecimalMin("0", inclusive = true)
    var salary: BigDecimal = BigDecimal.ZERO,

    @get:DecimalMin("0", inclusive = true)
    var liability: BigDecimal = BigDecimal.ZERO,

    @get:DecimalMin("0", inclusive = true)
    var requestedAmount: BigDecimal = BigDecimal.ZERO,

    @get:PositiveOrZero
    var requestedTerm: Long = 0
)