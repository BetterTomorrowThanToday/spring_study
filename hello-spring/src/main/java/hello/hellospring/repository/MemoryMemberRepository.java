package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>(); //동시성 문제 때문에 concurrent map 해야하지만 일단 ㄱ
    private static long sequence = 0L; // 위와 같은 문제로 atom long 해야하지만 일단 ㄱ

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //lambda 식, 공부하자
                .filter(member -> member.getName().equals(name)) //map에서 하나라도 같으면 리턴, 없으면 null 포함 return
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
