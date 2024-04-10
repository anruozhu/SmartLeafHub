package com.anranruozhu.service;

public interface DataAccess {
    void SaveSersor(String message);
    void SaveSoil(String message);
    void SaveDeviceState(int pumpCtrlState,
                         int pumpPowerState,
                         int fanMode,
                         int fanLevel);
    void SaveInstructions(int lightMode, int lightLevel);
}
