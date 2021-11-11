package com.ivyclub.data

import com.ivyclub.data.model.*

interface ContactRepository {
    fun loadFriends(): List<FriendData>
    fun saveFriend(friendData: FriendData)
    fun setShowOnBoardingState(state: Boolean)
    fun getShowOnBoardingState(): Boolean
    fun setNotificationTime(start: String, end: String)
    fun setNotificationOnOff(state: Boolean)
    fun getPlanList(): List<SimplePlanData>
    fun getPlanDataById(planId: Long): PlanData
    fun savePlanData(planData: PlanData, lastParticipants: List<Long> = emptyList())
    fun deletePlanData(planData: PlanData)
    fun getSimpleFriendDataListByGroup(groupName: String): List<SimpleFriendData>
    fun getSimpleFriendDataById(friendId: Long): SimpleFriendData
    fun getSimpleFriendData(): List<SimpleFriendData>
    fun loadGroups(): List<GroupData>
    fun saveNewGroup(groupData: GroupData)
    fun setFavorite(id: Long, state: Boolean)
    fun getPlanDetailsByTitle(title: String): PlanData
    fun getFriendDataById(id: Long): FriendData
    fun getPlansByIds(planIds: List<Long>): List<PlanData>
    fun updateGroupOf(targetFriend: List<Long>, targetGroup: String)
    fun updateFriend(
        phoneNumber: String,
        name: String,
        birthday: String,
        groupName: String,
        extraInfo: Map<String, String>,
        id: Long
    )
}