package barmin.twino.loans

import barmin.twino.loans.core.service.LoanService
import barmin.twino.loans.core.service.ScoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LoansApplicationTest {

    @Autowired
    lateinit var loanService: LoanService

    @Autowired
    lateinit var scoreService: ScoreService

}
