package com.sebasorozcob.www.movietail.view.fragments.movies.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.sebasorozcob.www.movietail.databinding.MoviesBottomSheetBinding

private const val TAG = "RecipesBottomSheet"

class MoviesBottomSheet : BottomSheetDialogFragment() {

//    private val recipesViewModel: RecipesViewModel by activityViewModels()
    private var _binding: MoviesBottomSheetBinding? = null
    private val binding get() = _binding!!
//
//    private var mealTypeChip = DEFAULT_MEAL_TYPE
//    private var mealTypeChipId = 0
//    private var dietTypeChip = DEFAULT_DIET_TYPE
//    private var dietTypeChipId = 0

    private var anyChange = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = MoviesBottomSheetBinding.inflate(inflater, container, false)

        var lastMealTypeCheck: String? = null
        var lastDietTypeCheck: String? = null

        // This observe receive the preferences saved previously
//        recipesViewModel.readMealAndDietType.asLiveData().observe(viewLifecycleOwner) { value ->
//
//            mealTypeChip = value.selectedMealType
//            lastMealTypeCheck = mealTypeChip
//            dietTypeChip = value.selectedDietType
//            lastDietTypeCheck = dietTypeChip
//
//            updateChip(value.selectedMealTypeId, binding.mealTypeChipGroup)
//            updateChip(value.selectedDietTypeId, binding.dietTypeChipGroup)
//        }

//        binding.mealTypeChipGroup.setOnCheckedChangeListener { group, selectedChipId ->
//            val chip = group.findViewById<Chip>(selectedChipId)
//            val selectedMealType = chip.text.toString().lowercase(Locale.ROOT)
//            mealTypeChip = selectedMealType
//            mealTypeChipId = selectedChipId
//
//            anyChange = mealTypeChip != lastMealTypeCheck
//            Log.d(
//                TAG,
//                "new meal type checked: $mealTypeChip, last meal type checked: $lastMealTypeCheck"
//            )
//        }

//        binding.dietTypeChipGroup.setOnCheckedChangeListener { group, selectedChipId ->
//            val chip = group.findViewById<Chip>(selectedChipId)
//            val selectedDietType = chip.text.toString().lowercase(Locale.ROOT)
//            dietTypeChip = selectedDietType
//            dietTypeChipId = selectedChipId
//
//            anyChange = dietTypeChip != lastDietTypeCheck
//            Log.d(
//                TAG,
//                "new diet type checked: $dietTypeChip, last diet type checked: $lastDietTypeCheck"
//            )
//
//        }

        binding.applyButton.setOnClickListener {

//            recipesViewModel.saveMealAndDietTypeTemp(
//                mealTypeChip,
//                mealTypeChipId,
//                dietTypeChip,
//                dietTypeChipId
//            )

            anyChange = if (anyChange) {
                false
        //                val action =
        //                    RecipesBottomSheetDirections.actionRecipesBottomSheetToRecipesFragment(
        //                        backFromBottomSheet = true
        //                    )
        //                findNavController().navigate(action)
            } else {
                false
        //                val action =
        //                    RecipesBottomSheetDirections.actionRecipesBottomSheetToRecipesFragment(
        //                        backFromBottomSheet = false
        //                    )
        //                findNavController().navigate(action)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}