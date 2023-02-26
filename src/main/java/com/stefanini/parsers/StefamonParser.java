package com.stefanini.parsers;

import com.stefanini.dto.StefamonDTO;
import com.stefanini.entities.Stefamon;

public class StefamonParser {

    public static Stefamon dtoToEntity(StefamonDTO dto) {
        Stefamon stefamon = new Stefamon(dto);
        return stefamon;
    }

    public static StefamonDTO entityToDTO(Stefamon stefamon) {
        return new StefamonDTO(stefamon);
    }

}
