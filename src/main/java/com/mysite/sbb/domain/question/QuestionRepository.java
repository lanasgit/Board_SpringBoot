package com.mysite.sbb.domain.question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// JpaRepository를 상속할 때는 제네릭스 타입으로 <Question, Integer> 처럼 리포지터리의 대상이 되는 엔티티의 타입(Question)과 해당 엔티티의 PK의 속성 타입(Integer)을 지정해야 한다.
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject, String content);
    List<Question> findBySubjectLike(String subject);
    Page<Question> findAll(Pageable pageable);

    // @Query를 작성할 때에는 반드시 테이블 기준이 아닌 엔티티 기준으로 작성해야 한다. (테이블명 대신 엔티티명 사용, 컬럼명 대신 엔티티 속성명 사용)
    // 그리고 @Query에 파라미터로 전달할 kw 문자열은 메서드의 매개변수에 @Param("kw")처럼 @Param 애너테이션을 사용해야 한다. 검색어를 의미하는 kw 문자열은 @Query 안에서 :kw로 참조된다.
    @Query("select "
            + "distinct q "
            + "from Question q "
            + "left outer join SiteUser u1 on q.author=u1 "
            + "left outer join Answer a on a.question=q "
            + "left outer join SiteUser u2 on a.author=u2 "
            + "where "
            + "   q.subject like %:kw% "
            + "   or q.content like %:kw% "
            + "   or u1.username like %:kw% "
            + "   or a.content like %:kw% "
            + "   or u2.username like %:kw% ")
    Page<Question> findAllByKeyword(@Param("kw") String kw, Pageable pageable);
}