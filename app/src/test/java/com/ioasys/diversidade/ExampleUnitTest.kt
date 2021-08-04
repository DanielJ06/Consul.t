package com.ioasys.diversidade

import com.ioasys.diversidade.domain.repository.ProfessionalRepository
import com.ioasys.diversidade.utils.ViewState.Success
import com.ioasys.diversidade.presentation.viewmodels.ProfessionalViewModel
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ExampleUnitTest {

    private lateinit var viewModel: ProfessionalViewModel

    private val professionalRepository: ProfessionalRepository = mockk()

    @Before
    fun setUp() {
        viewModel = ProfessionalViewModel(professionalRepository)
    }

    @Test
    fun `Should return MissingParamsException if no params was provided`() {

        viewModel.loadProfessionals()

        val any: Any = Unit

        assertEquals(Success(any), viewModel.professionals.value)
    }
}