package entity;
/**
 * A test suite that holds artist and song tests
 * @author CJ
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ArtistTest.class, SongTest.class })
public class AllTests {

}
