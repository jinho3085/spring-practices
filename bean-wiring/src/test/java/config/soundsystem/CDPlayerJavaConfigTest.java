package config.soundsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import soundsystem.CDPlayer;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={CDPlayerConfig.class})
public class CDPlayerJavaConfigTest {
	@Autowired
	CDPlayer cdPlayer;
	
	@Test
	public void testCDPlayerNotNull() {
		assertNotNull(cdPlayer);
	}
	
	@Test
	public void testPlay() {
		assertEquals("playing 붕붕 by 김하온", cdPlayer.play());
	}
}