package com.mech.multiplayer.packets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PlayerPacket implements Serializable {

    public static long serialVersionUID = 1L;
    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private PlayerPacketType type;
    private String packet;
}
