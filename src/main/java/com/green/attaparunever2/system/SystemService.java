package com.green.attaparunever2.system;

import com.green.attaparunever2.admin.SystemPostRepository;
import com.green.attaparunever2.common.MyFileUtils;
import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.entity.SystemPost;
import com.green.attaparunever2.system.model.UpdSystemPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SystemService {
    private final SystemPostRepository systemPostRepository;
    private final MyFileUtils myFileUtils;

    public int patchSystemPost(MultipartFile pic, UpdSystemPostReq req) {
        SystemPost systemPost = systemPostRepository.findById(req.getInquiryId())
                .orElseThrow(() -> new CustomException("게시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        if (!systemPost.getId().equals(req.getId())) {
            throw new CustomException("게시글 수정 권한이 없습니다.", HttpStatus.BAD_REQUEST);
        }

        Long systemId = systemPost.getInquiryId();

        systemPost.setInquiryTitle(req.getInquiryTitle());
        systemPost.setInquiryDetail(req.getInquiryDetail());

        // 저장할 파일명 생성
        String savedPicName = pic != null ? myFileUtils.makeRandomFileName(pic) : null;

        // 폴더 만들기
        String folderPath = String.format("systemPost/%d", systemId);
        myFileUtils.makeFolders(folderPath);

        // 기존 파일 삭제
        String deletePath = String.format("%s/systemPost/%d", myFileUtils.getUploadPath(), systemId);
        myFileUtils.deleteFolder(deletePath, false);

        systemPost.setPic(savedPicName);
        systemPostRepository.save(systemPost);

        if ((pic == null || pic.isEmpty()) && (systemPost.getPic() == null || systemPost.getPic().isEmpty())) {
            return 0;
        }

        // 파일 이동
        String filePath = String.format("systemPost/%d/%s", systemId, savedPicName);
        try {
            myFileUtils.transferTo(pic, filePath); // MultipartFile을 파일로 저장
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("파일 이동 실패");
        }

        return 1;
    }
}
