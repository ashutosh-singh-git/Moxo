package com.moxo.app.dto.paw;

import lombok.Data;

@Data
public class Tasks {
    private ServiceType type;
    private int completedTasks;
}
