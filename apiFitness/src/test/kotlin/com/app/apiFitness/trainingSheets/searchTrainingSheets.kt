package com.app.apiFitness.trainingSheets

import com.app.apiFitness.service.TrainingSheetServiceImpl
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired

@ExtendWith(MockKExtension::class)
@DisplayName("All kind search in trainingSheets")
class SearchTrainingSheetsImplTest {

    @MockK(relaxed = true)
    private lateinit var trainingSheetServiceImpl: TrainingSheetServiceImpl

    @Test
    fun `search all trainingSheets from teacher`() {
        val userId = 1

    }

}