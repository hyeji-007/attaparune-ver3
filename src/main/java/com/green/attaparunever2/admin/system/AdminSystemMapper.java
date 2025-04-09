package com.green.attaparunever2.admin.system;

import com.green.attaparunever2.admin.system.model.*;
import com.green.attaparunever2.user.model.GetReviewReq;
import com.green.attaparunever2.user.model.GetReviewRequestDto;
import com.green.attaparunever2.user.model.GetReviewRequestReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminSystemMapper {
    List<SelSystemPostCommentRes> selSystemPostComment(SelSystemPostCommentReq req);
    SelSettlementDetailRes res = new SelSettlementDetailRes();
    List<SelSettlementDetailRes> selSettlementDetail(SelSettlementDetailReq req);
    List<GetReviewRequestDto> getReviewRequestList(GetReviewRequestReq p);

    List<SelSystemPostRes> selSystemPost(GetSystemPostReq req);
    int selSystemPostTotalCount();

    SelSystemPostPercentageRes selSystemPostPercentage();
}
