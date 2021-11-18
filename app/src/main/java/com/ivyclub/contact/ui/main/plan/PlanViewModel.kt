package com.ivyclub.contact.ui.main.plan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivyclub.contact.ui.plan_list.PlanListItemViewModel
import com.ivyclub.data.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanViewModel @Inject constructor(
    private val repository: ContactRepository
) : ViewModel() {

    private val _planListItems = MutableLiveData<List<PlanListItemViewModel>>()
    val planListItems: LiveData<List<PlanListItemViewModel>> = _planListItems

    private val friendMap = mutableMapOf<Long, String>()

    private val loadFriendsJob: Job = viewModelScope.launch {
        repository.getSimpleFriendData()?.forEach {
            friendMap[it.id] = it.name
        }
    }

    init { getMyPlans() }

    private fun getMyPlans() {
        viewModelScope.launch {
            loadFriendsJob.join()

            repository.loadPlanListWithFlow().buffer().collect { newPlanList ->
                val planItems = mutableListOf<PlanListItemViewModel>()
                newPlanList.forEach { planData ->
                    val friends = mutableListOf<String>()
                    planData.participant.forEach { friendId ->
                        friendMap[friendId]?.let { friendName ->
                            friends.add(friendName)
                        }
                    }
                    planItems.add(
                        PlanListItemViewModel(planData, friends)
                    )
                }

                _planListItems.value = planItems
            }
        }
    }
}