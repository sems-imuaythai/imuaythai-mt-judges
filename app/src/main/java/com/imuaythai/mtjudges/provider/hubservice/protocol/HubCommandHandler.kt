package com.imuaythai.mtjudges.provider.hubservice.protocol

interface HubCommandHandler{

    fun accept(visitor: HubCommandVisitor)

}