package com.blbd.volunteer;

import com.blbd.volunteer.dao.ChatFriendListEntityMapper;
import com.blbd.volunteer.dao.ChatLinkEntityMapper;
import com.blbd.volunteer.dao.ChatMsgEntityMapper;
import com.blbd.volunteer.dao.entity.ChatFriendListEntity;
import com.blbd.volunteer.dao.entity.ChatLinkEntity;
import com.blbd.volunteer.dao.entity.ChatMsgEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 测试类记得在springbootTest中加上webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
 * SpringBootTest在启动的时候不会启动服务器，所以WebSocket自然会报错，这个时候需要添加选项webEnvironment，以便提供一个测试的web环境。
 */
@SpringBootTest(classes = VolunteerApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Rollback  //自动回滚
@Transactional
public class ChatApplicationTests {
    @Autowired
    ChatFriendListEntityMapper chatFriendListEntityMapper;

    @Test
    public void insertTest() {
        ChatFriendListEntity chatFriendListEntity = new ChatFriendListEntity();
        chatFriendListEntity.setLinkId("1");
        chatFriendListEntity.setSenderId("1");
        chatFriendListEntity.setReceiverId("2");
        chatFriendListEntity.setReceiverPicture("580gadaf");
        int i = chatFriendListEntityMapper.insert(chatFriendListEntity);
        assert i == 1;

    }

    @Test
    public void deleteByLinkIdTest() {
        ChatFriendListEntity chatFriendListEntity = new ChatFriendListEntity();
        chatFriendListEntity.setLinkId("test");
        chatFriendListEntity.setSenderId("1");
        chatFriendListEntity.setReceiverId("2");
        chatFriendListEntity.setReceiverPicture("580gadaf");
        assert chatFriendListEntityMapper.deleteByLinkId(chatFriendListEntity) == 1;

    }
    @Test
    public void selectBySenderIdTest() {
        ChatFriendListEntity chatFriendListEntity = new ChatFriendListEntity();
        chatFriendListEntity.setLinkId("test");
        chatFriendListEntity.setSenderId("1");
        chatFriendListEntity.setReceiverId("2");
        chatFriendListEntity.setReceiverPicture("580gadaf");
        List<ChatFriendListEntity> list = chatFriendListEntityMapper.selectBySenderId(chatFriendListEntity);
        assert list.size() != 0;
    }
    @Test
    public void modifyCFLETest() {
        ChatFriendListEntity chatFriendListEntity = new ChatFriendListEntity();
        chatFriendListEntity.setListId(1);
        chatFriendListEntity.setLinkId("test");
        chatFriendListEntity.setSenderId("1");
        chatFriendListEntity.setReceiverId("2");
        chatFriendListEntity.setReceiverPicture("eee222");
        assert chatFriendListEntityMapper.modify(chatFriendListEntity) == 1;
    }

    @Autowired
    ChatLinkEntityMapper chatLinkEntityMapper;

    @Test
    public void insertCLETest() {
        ChatLinkEntity chatLinkEntity = new ChatLinkEntity();
        chatLinkEntity.setLinkId("12aa");
        chatLinkEntity.setSenderId("1");
        chatLinkEntity.setReceiverId("2");
        chatLinkEntity.setCreateTime(new Date(System.currentTimeMillis()));
        assert chatLinkEntityMapper.insert(chatLinkEntity) == 1;
    }
    @Test
    public void deleteByLinkId() {
        ChatLinkEntity chatLinkEntity = new ChatLinkEntity();
        chatLinkEntity.setLinkId("test");
        chatLinkEntity.setSenderId("1");
        chatLinkEntity.setReceiverId("2");
        chatLinkEntity.setCreateTime(new Date(System.currentTimeMillis()));
        assert chatLinkEntityMapper.deleteByLinkId(chatLinkEntity) == 1;
    }
    @Test
    public void selectByLinkId() {
        ChatLinkEntity chatLinkEntity = new ChatLinkEntity();
        chatLinkEntity.setLinkId("test");
        chatLinkEntity.setSenderId("1");
        chatLinkEntity.setReceiverId("2");
        chatLinkEntity.setCreateTime(new Date(System.currentTimeMillis()));
        List<ChatLinkEntity> list = chatLinkEntityMapper.selectByLinkId(chatLinkEntity);
        assert list.size() != 0;
    }

    @Autowired
    ChatMsgEntityMapper chatMsgEntityMapper;
    @Test
    public void insertCMETest() {
        ChatMsgEntity chatMsgEntity = new ChatMsgEntity();
        chatMsgEntity.setLinkId("dfg");
        chatMsgEntity.setSenderId("2");
        chatMsgEntity.setReceiverId("1");
        chatMsgEntity.setMsgType(2);
        chatMsgEntity.setMsgBody("你好啊");
        chatMsgEntity.setIsLatest(1);
        assert chatMsgEntityMapper.insert(chatMsgEntity) == 1;
    }
    @Test
    public void deleteByMessageId() {
        ChatMsgEntity chatMsgEntity = new ChatMsgEntity();
        chatMsgEntity.setMessageId(1);
        chatMsgEntity.setLinkId("dfg");
        chatMsgEntity.setSenderId("2");
        chatMsgEntity.setReceiverId("1");
        chatMsgEntity.setMsgType(2);
        chatMsgEntity.setMsgBody("你好啊");
        chatMsgEntity.setIsLatest(1);
        assert chatMsgEntityMapper.deleteByMessageId(chatMsgEntity) == 1;
    }
    @Test
    public void modifyCME() {
        ChatMsgEntity chatMsgEntity = new ChatMsgEntity();
        chatMsgEntity.setMessageId(1);
        chatMsgEntity.setLinkId("test");
        chatMsgEntity.setSenderId("2");
        chatMsgEntity.setReceiverId("1");
        chatMsgEntity.setMsgType(2);
        chatMsgEntity.setMsgBody("你好啊");
        chatMsgEntity.setIsLatest(0);
        assert chatMsgEntityMapper.modify(chatMsgEntity) == 1;
    }
    @Test
    public void selectMSGByLinkId() {
        ChatMsgEntity chatMsgEntity = new ChatMsgEntity();
        chatMsgEntity.setLinkId("test");
        chatMsgEntity.setSenderId("2");
        chatMsgEntity.setReceiverId("1");
        chatMsgEntity.setMsgType(2);
        chatMsgEntity.setMsgBody("你好啊");
        chatMsgEntity.setIsLatest(0);
        chatMsgEntityMapper.insert(chatMsgEntity);
        List<ChatMsgEntity> list = chatMsgEntityMapper.selectByLinkId(chatMsgEntity);
        assert list.size() != 0;
    }

}
