package org.example.Bot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TgUser {
    private Long chatId;
    private Tgstate tgstate;
}
