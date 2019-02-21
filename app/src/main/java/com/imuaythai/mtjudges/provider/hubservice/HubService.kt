package com.imuaythai.mtjudges.provider.hubservice

import com.imuaythai.mtjudges.provider.hubservice.service.HubConnectionService
import com.imuaythai.mtjudges.provider.hubservice.service.HubMainJudgeService
import com.imuaythai.mtjudges.provider.hubservice.service.HubPointJudgeService
import com.imuaythai.mtjudges.provider.hubservice.service.HubTimeKeeperService

interface HubService :
    HubConnectionService,
    HubTimeKeeperService,
    HubPointJudgeService,
    HubMainJudgeService {
}