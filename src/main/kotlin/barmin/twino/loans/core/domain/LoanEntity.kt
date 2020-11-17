package barmin.twino.loans.core.domain

import barmin.twino.loans.api.dto.LoanStatus
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "LOANS")
data class LoanEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val personalId: Long?,

    val firstName: String,

    val lastName: String?,

    val birthDate: LocalDate,

    val employer: String?,

    val salary: BigDecimal = BigDecimal.ZERO,

    val liability: BigDecimal = BigDecimal.ZERO,

    val requestedAmount: BigDecimal = BigDecimal.ZERO,

    val requestedTerm: Long = 0,

    @Enumerated(EnumType.STRING)
    var status: LoanStatus? = null
)