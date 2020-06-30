package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    /** Entity 를 가져오는 방식이기 때문에 불필요한 orders 까지 다 가져오게 되는 단점이 있다. **/
    /** 이 방법은 또 Entity 에 @JsonIgnore 추가를 통해 반영할 수 있다. **/
    /** 하지만 이 방법은 다양한 case 에서 매번 @JsonIgnore 를 군데군데 추가해야 하고, Entity 에 Logic이 추가되는 단점이 있다. **/
    /** 또 Entity 안에서 name -> username 으로 교체하는 경우 API스펙(JSON) 도 바뀐 상태로 GET 한다는 단점이 있다. **/
    @GetMapping("/api/v1/members")
    public List<Member> membersV1(){
        return memberService.findMembers();
    }

    /** 별도의 DTO 를 만들어 사용하면, 별도로 요구하는 Data 를 추가하거나 소거하기도 편하다. **/
    @GetMapping("/api/v2/members")
    public Result membersV2(){
        List<MemberDTO> collect = memberService.findMembers().stream().map(m -> new MemberDTO(m.getName(),m.getAddress()))
                .collect(Collectors.toList());

        return new Result(collect.size(),collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDTO{
        private String name;
        private Address address;
    }


    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    /** 별도의 DTO를 받아서 사용하는 것 **/
    /** Entity 와 API 를 명확하게 분리시킬 수 있고, Entity 에서 발생하는 필드네임교체나 변화에 compile시 대응해 줄수 있고 API에 영향이 안가는 장점이있다.**/
    /** API를 항상 Entity 를 직접 사용하지 않고, 별도의 DTO 를 사용해야 한다 **/
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){

        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberRequest request){

        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);//커맨드와 쿼리를 분리하는 스타일
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

    @Data
    static class UpdateMemberRequest{
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String name;
    }


    @Data
    static class CreateMemberRequest{
        @NotEmpty
        private String name;
    }

    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
