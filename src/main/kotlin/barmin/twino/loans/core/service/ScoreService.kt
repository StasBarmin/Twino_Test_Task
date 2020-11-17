package barmin.twino.loans.core.service

import barmin.twino.loans.api.dto.ScoreDTO
import barmin.twino.loans.core.domain.LoanEntity

interface ScoreService {

    fun scoreLoan(loan: LoanEntity): ScoreDTO
}