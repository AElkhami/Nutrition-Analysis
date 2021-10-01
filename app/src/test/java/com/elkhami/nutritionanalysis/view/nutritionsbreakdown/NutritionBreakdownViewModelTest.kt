package com.elkhami.nutritionanalysis.view.nutritionsbreakdown

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.elkhami.nutritionanalysis.data.stub.NutritionFactsStub
import com.elkhami.nutritionanalysis.data.repository.TestRepository
import com.elkhami.nutritionanalysis.other.TestCoroutineRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * Created by A.Elkhami on 30,September,2021
 */
class NutritionBreakdownViewModelTest {

    @get:Rule
    var testRule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: NutritionBreakdownViewModel

    @Before
    fun setUp() {
        viewModel = NutritionBreakdownViewModel(TestRepository())
    }

    @Test
    fun `make sure that the required nutrition breakdown data received based on stub`() {
        viewModel.getNutritionalDataForIngredients(listOf("Carrot 50 g"))

        val stub = NutritionFactsStub

        assertThat(viewModel.nutritionBreakdownList.value).isEqualTo(stub.nutritionBreakdownList)
    }



}