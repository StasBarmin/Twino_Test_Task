package barmin.twino.loans.core.service

import barmin.twino.loans.api.dto.LoanStatus
import barmin.twino.loans.api.dto.ScoreDTO
import barmin.twino.loans.core.domain.LoanEntity
import org.springframework.stereotype.Service

@Service
class ScoreServiceImpl : ScoreService {

    override fun scoreLoan(loan: LoanEntity): ScoreDTO {
    val score = loan.let {
        it.firstName.sumOfLettersPositions() +
            it.salary.toDouble() * 1.5 -
            it.liability.toDouble() * 3 +
            it.birthDate.year -
            it.birthDate.monthValue -
            it.birthDate.dayOfYear
        }
        val status = when {
            score < 2500 -> LoanStatus.REJECTED
            score > 3500 -> LoanStatus.APPROVED
            else -> LoanStatus.MANUAL
        }
        return ScoreDTO(loan.personalId!!, status)
    }

    private fun String.sumOfLettersPositions() = toCharArray()
        .map {
            lettersPositions.getOrElse(it) {
                throw IllegalArgumentException("Illegal character was used in first name '$this'")
            }
        }
        .sum()

    private val lettersPositions = ('a'..'z').let {
        var counter = 1
        it.associateWith { counter++ }
    }
}