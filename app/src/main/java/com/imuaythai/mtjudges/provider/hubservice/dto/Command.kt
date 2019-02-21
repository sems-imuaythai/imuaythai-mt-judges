package com.imuaythai.mtjudges.provider.hubservice.dto

enum class Command {
    FightListReceived,
    EnteredToFight,
    FightResumed,
    FightPaused,
    FightEnded,
    PointsSent,
    PointsAccepted,
    PrematureEnded,
    InjurySaved,
    JudgeSentPoints,
    PointsSaved,
    RoundStarted,
    RoundEnded,
    BreakEnded
}