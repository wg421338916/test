package com.wanggang.springbootmybatisstudy;

import com.wanggang.GeneratorCode.gen.service.GenServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootmybatisstudyApplicationTests {

    @Autowired
    GenServiceImpl genService;

    @Test
    public void contextLoads() throws IOException {
        byte[] sxyp_im_friend_rels = genService.generatorCode("sxyp_im_friend_rel");

        String path = "E://temp.zip";

        OutputStream out = new FileOutputStream(new File(path));

        InputStream in = new ByteArrayInputStream(sxyp_im_friend_rels);

        org.apache.commons.io.IOUtils.copy(in, out);

        in.close();
        out.close();
    }

}
