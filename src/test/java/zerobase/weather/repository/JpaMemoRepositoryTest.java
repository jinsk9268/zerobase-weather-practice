package zerobase.weather.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaMemoRepositoryTest {
    @Autowired
    JpaMemoRepository jpaMemoRepository;

    @Test
    void insertMemoTest() {
        // given
        Memo newMemo = new Memo(10, "this is jpa memo");

        // when
        jpaMemoRepository.save(newMemo);

        // then
        List<Memo> memoList = jpaMemoRepository.findAll();
        assertTrue(memoList.size() > 0);
    }

    @Test
    void findByIdTest() {
        // given
        Memo memo = new Memo(11, "jpa");

        // when
        Memo memoSaved = jpaMemoRepository.save(memo);
        System.out.println(memoSaved.getId());

        // then
        Optional<Memo> result = jpaMemoRepository.findById(memoSaved.getId());
        assertEquals(result.get().getText(), "jpa");
    }
}