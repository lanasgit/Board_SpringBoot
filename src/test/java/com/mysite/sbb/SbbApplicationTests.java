package com.mysite.sbb;

import com.mysite.sbb.domain.answer.AnswerRepository;
import com.mysite.sbb.domain.question.QuestionRepository;
import com.mysite.sbb.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest: SbbApplicationTests 클래스가 스프링부트 테스트 클래스임을 의미한다.
@SpringBootTest
class SbbApplicationTests {

    // @Autowired: 스프링의 DI 기능으로 questionRepository 객체를 스프링이 자동으로 생성해 준다.
    // 테스트 코드의 경우에는 생성자를 통한 객체의 주입이 불가능하므로 테스트 코드 작성시에만 @Autowired를 사용하고 실제 코드 작성시에는 생성자를 통한 객체 주입방식을 사용한다.
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionService questionService;

//    @Test // save
//    void testJpa() {
//        Question q1 = new Question();
//        q1.setSubject("sbb가 무엇인가요?");
//        q1.setContent("sbb에 대해서 알고 싶습니다.");
//        q1.setCreateDate(LocalDateTime.now());
//        this.questionRepository.save(q1);  // 첫번째 질문 저장
//
//        Question q2 = new Question();
//        q2.setSubject("스프링부트 모델 질문입니다.");
//        q2.setContent("id는 자동으로 생성되나요?");
//        q2.setCreateDate(LocalDateTime.now());
//        this.questionRepository.save(q2);  // 두번째 질문 저장
//    }

//    @Test // findAll
//    void testJpa() {
//        List<Question> all = this.questionRepository.findAll();
//        assertEquals(2, all.size());
//
//        Question q = all.get(0);
//        assertEquals("sbb가 무엇인가요?", q.getSubject());
//    }

//    @Test // findById
//    void testJpa() {
//        // findById의 리턴 타입은 Question이 아닌 Optional임에 주의
//        Optional<Question> oq = this.questionRepository.findById(1);
//        if(oq.isPresent()) {
//            Question q = oq.get();
//            assertEquals("sbb가 무엇인가요?", q.getSubject());
//        }
//    }

//    @Test // findBySubject(리포지토리 인터페이스에 추가)
//    void testJpa() {
//        Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
//        assertEquals(1, q.getId());
//    }

//    @Test // findBySubjectAndContent(리포지토리 인터페이스에 추가)
//    void testJpa() {
//        Question q = this.questionRepository.findBySubjectAndContent(
//                "sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
//        assertEquals(1, q.getId());
//    }

//    @Test // findBySubjectLike(리포지토리 인터페이스에 추가) : 제목에 특정 문자열이 포함되어 있는 데이터를 조회
//    void testJpa() {
//        List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
//        Question q = qList.get(0);
//        assertEquals("sbb가 무엇인가요?", q.getSubject());
//    }

//    @Test // 데이터 수정하기
//    void testJpa() {
//        Optional<Question> oq = this.questionRepository.findById(1);
//        assertTrue(oq.isPresent());
//        Question q = oq.get();
//        q.setSubject("수정된 제목");
//        this.questionRepository.save(q);
//    }

//    @Test // 데이터 삭제하기
//    void testJpa() {
//        assertEquals(2, this.questionRepository.count());
//        Optional<Question> oq = this.questionRepository.findById(1);
//        assertTrue(oq.isPresent());
//        Question q = oq.get();
//        this.questionRepository.delete(q);
//        assertEquals(1, this.questionRepository.count()); // 리포지터리의 count() 메서드는 해당 리포지터리의 총 데이터건수를 리턴한다.
//        // 삭제하기 전에는 데이터 건수가 2, 삭제한 후에는 데이터 건수가 1인지를 테스트했다.
//    }

//    @Test // 답변 데이터 생성 후 저장하기
//    void testJpa() {
//        Optional<Question> oq = this.questionRepository.findById(2);
//        assertTrue(oq.isPresent());
//        Question q = oq.get();
//
//        Answer a = new Answer();
//        a.setContent("네 자동으로 생성됩니다.");
//        a.setQuestion(q);  // 어떤 질문의 답변인지 알기위해서 Question 객체가 필요하다.
//        a.setCreateDate(LocalDateTime.now());
//        this.answerRepository.save(a);
//    }

//    // Question 리포지터리가 findById를 호출하여 Question 객체를 조회하고 나면 DB세션이 끊어지기 때문에 오류 발생 -> @Transactional 사용.
//    // 이 문제는 테스트 코드에서만 발생한다. 실제 서버에서 JPA 프로그램들을 실행할 때는 DB 세션이 종료되지 않기 때문에 오류가 발생하지 않는다.
//    @Transactional
//    @Test // 질문에 달린 답변 찾기
//    void testJpa() {
//        Optional<Question> oq = this.questionRepository.findById(2);
//        assertTrue(oq.isPresent());
//        Question q = oq.get();
//
//        List<Answer> answerList = q.getAnswerList();
//
//        assertEquals(1, answerList.size());
//        assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
//    }

//    // test 데이터 300개 생성
//    @Test
//    void testJpa() {
//        for (int i = 1; i <= 300; i++) {
//            String subject = String.format("테스트 데이터입니다:[%03d]", i);
//            String content = "내용무";
//            this.questionService.create(subject, content);
//        }
//    }
}
