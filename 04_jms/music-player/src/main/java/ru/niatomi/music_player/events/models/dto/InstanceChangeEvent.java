package ru.niatomi.music_player.events.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Data
@AllArgsConstructor
@Jacksonized @Builder
@ToString
public class InstanceChangeEvent {

    private String instanceName;
    private String attributeName;
    private Integer entityId;
    private Object oldValue;
    private Object newValue;

}
