package com.tenco.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Ioc
@RequiredArgsConstructor // DI 처리 됨
public class BoardPersistRepository {

    // JPA 핵심 인터페이스
    // 영속성 컨텍스트를 관리하고 엔티티의 생명주기를 제어
    // @Autowired // DI
    private final EntityManager em; // final 사용하면 성능 개선이 조금 됨

    // 의존 주입 (외부에서 생성되어 있는 객체의 주소값을 주입 받다)
//    public BoardPersistRepository(EntityManager em) {
//        this.em = em;
//    }

    // 게시글 작성
    @Transactional
    public Board save(Board board) {
        em.persist(board); // insert 처리 완료
        return board;
    }

    // JPQL를 사용한 목록 조회
    public List<Board> findAll() {
        // JOIN FETCH 사용 쿼리 변경함
        // N + 1 문제를 해결하는 정밀 제어. JOIN FETCH 은 그냥 한번에 다 가져와
        String jpql = "select b from Board b join fetch b.user order by b.id DESC";
        List<Board> boardList = em.createQuery(jpql, Board.class).getResultList();

        return boardList;
    }

    // 게시글 상세보기 요청 (조회) (필수값 기본키로 조회)
    public Board findById(Integer id) {
        String jpql = """
        SELECT b FROM Board b
        JOIN FETCH b.user
        WHERE b.id = :id
    """;

        return em.createQuery(jpql, Board.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    // 게시글 삭제
    @Transactional
    public void deleteById(Integer id) {
        // 1. 먼저 삭제 하고자하는 엔티티를 조회
        // 1.1 조회가 되었기 때문에 board 는 영속화 된 상태가 되었다.
        Board board = em.find(Board.class, id);
        if (board == null) {
            throw new IllegalArgumentException("삭제할 게시글을 찾을 수 없습니다 : " + id);
        }
        em.remove(board);
    }

    @Transactional
    public void updateById(Integer id, BoardRequest.UpdateDTO updateDTO) {
        // 수정시 항상 조회 먼저 확인
        Board boardEntity = em.find(Board.class, id);
        // em.find() 호출 수 리턴 받은 board 는 영속 상태가 되어 졌다.

        if (boardEntity == null) {
            throw new IllegalArgumentException("수정할 게시글을 찾을 수 없습니다 : " + id);
        }
        boardEntity.update(updateDTO);
        // 변경 감지(Dirty Checking) 동작 됨.
        // 영속 컨텍스트에 관리 되어지는 객체(엔티티)안에 조회 했을 때 기준으로 1차 캐쉬에 저장되어 짐
        // 추후 1차 캐쉬에 들어가 있는 객체의(엔티티의) 변수값이 변경 되었다면 자동으로 감지 한다.
        // 그냥 새로은 보드 생성
        //em.persist(boardEntity);

        // 앞으로 수정 기능을 만들어 줄 때 더티 체킹 동작으로 사용하자.
    }



}
