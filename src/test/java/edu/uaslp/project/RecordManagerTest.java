package edu.uaslp.project;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordManagerTest {

    @Test
    public void givenAnEmptyRecord_whenSave_thenRecordsSizeIsOne() {
        // given:
        RecordManager recordManager = new RecordManager(10, "test1.record");
        Record record = new Record("Juan", 50);

        // when:
        recordManager.save(record);

        List<Record> records = recordManager.getRecords();

        // then:
        Assertions.assertThat(records).contains(record);
    }

    @Test
    public void givenAnFullRecord_whenSave_thenRecordsSizeIsNotChanged() {
        // given:
        RecordManager recordManager = new RecordManager(2, "test2.record");

        recordManager.save(new Record("Juan", 50));
        Record maria = new Record("Maria", 60);
        recordManager.save(maria);

        // when:
        Record mario = new Record("Mario", 70);
        recordManager.save(mario);

        List<Record> records = recordManager.getRecords();

        // then:
        Assertions.assertThat(records).containsExactly(mario, maria);
    }

    @Test
    public void givenAnExistentRecord_whenSave_thenRecordIsPersisted() {
        // given:
        RecordManager recordManager = new RecordManager(2, "test3.record");

        Record juan = new Record("Juan", 80);
        recordManager.save(juan);

        // when:
        RecordManager recordManager2 = new RecordManager(2, "test3.record");
        Record mario = new Record("Mario", 70);
        recordManager2.save(mario);

        List<Record> records = recordManager.getRecords();

        // then:
        Assertions.assertThat(records).containsExactly(juan, mario);
    }

    @Test
    public void givenAnEmptyName_whenSave_thenThrowAnException() {
        // given:
        RecordManager recordManager = new RecordManager(2, "test4.record");

        Record juan = new Record("", 80);


        // when:
        Assertions.assertThatThrownBy(() -> recordManager.save(juan))
                .isInstanceOf(RecordsException.class)
                .hasMessage("Empty name not allowed");
    }

    @Test
    public void verifySorting() {
        // given:
        RecordManager recordManager = new RecordManager(5, "test5.record");

        // When:
        recordManager.save(new Record("Juan", 80));
        recordManager.save(new Record("Mario", 30));
        recordManager.save(new Record("Maria", 90));
        recordManager.save(new Record("Lupe", 70));
        recordManager.save(new Record("Pancho", 55));
        recordManager.save(new Record("Rodrigo", 80));
        recordManager.save(new Record("Gerardo", 35));

        List<Record> records = recordManager.getRecords();

        // Then:
        Assertions.assertThat(records).containsExactly(
                new Record("Maria", 90),
                new Record("Juan", 80),
                new Record("Rodrigo", 80),
                new Record("Lupe", 70),
                new Record("Pancho", 55));
    }
}