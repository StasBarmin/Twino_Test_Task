package barmin.twino.loans

import barmin.twino.loans.api.dto.LoanStatus
import barmin.twino.loans.core.domain.LoanEntity
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate

class ScoreServiceTest : LoansApplicationTest() {

    @Test
    fun scoreLoanRejected() {
        val loan = LoanEntity(
            personalId = 1,
            firstName = "aa",
            lastName = "test",
            birthDate = LocalDate.of(2000, 1, 5),
            employer = "test",
            salary = BigDecimal.TEN,
            liability = BigDecimal.ONE,
            requestedAmount = BigDecimal.TEN,
            requestedTerm = 5L
        )
        val score = scoreService.scoreLoan(loan)
        //according formula 2 + 15 + 3 + 2000 - 1 -5 = 2012 < 2500

        Assertions.assertEquals(LoanStatus.REJECTED.name, score.status.name)
    }

    @Test
    fun scoreLoanApproved() {
        val loan = LoanEntity(
            personalId = 1,
            firstName = "aa",
            lastName = "test",
            birthDate = LocalDate.of(2000, 1, 5),
            employer = "test",
            salary = BigDecimal(2000),
            liability = BigDecimal.ONE,
            requestedAmount = BigDecimal.TEN,
            requestedTerm = 5L
        )
        val score = scoreService.scoreLoan(loan)
        //according formula 2 + 15 + 6000 + 2000 - 1 -5 = 2012 < 2500

        Assertions.assertEquals(LoanStatus.APPROVED.name, score.status.name)
    }
}