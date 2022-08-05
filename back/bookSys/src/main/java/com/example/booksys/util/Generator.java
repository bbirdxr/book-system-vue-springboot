package com.example.booksys.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

public class Generator {
    public static void main(String[] args) {
        generator();

    }
    private static void generator(){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/book?serverTimezone=GMT%2b8", "root", "xurui2002")
                .globalConfig(builder -> {
                    builder.author("XuDaxia") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(
                                    "D:\\IdeaProjects\\bookSys\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.booksys") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\IdeaProjects\\bookSys\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
                    builder.controllerBuilder().enableHyphenStyle()
                                    .enableRestStyle();
                    builder.addInclude("Book","User") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .execute();
    }
}
