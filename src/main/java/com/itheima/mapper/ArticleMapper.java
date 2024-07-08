package com.itheima.mapper;

import com.itheima.annocation.AutoFill;
import com.itheima.enumeration.OperationType;
import com.itheima.pojo.Article;
import com.itheima.pojo.PageQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {

    /**
     * 新增文章
     *
     * @param article
     */
    @AutoFill(value = OperationType.INSERT)
    @Insert("insert into article (title, content, cover_img, category_id, create_user, create_time, update_time)" +
            " values (#{title},#{content},#{coverImg},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void insertArticle(Article article);

    /**
     * 根据条件进行分页查询
     *
     * @param pageQuery
     * @return
     */
    List<Article> listQuery(PageQuery pageQuery);

    /**
     * 根据id查询文章详情
     *
     * @param id
     * @return
     */
    @Select("select * from article where id =#{id}")
    Article selectArticleDetailById(Integer id);

    /**
     * 根据ID修改文章信息
     *
     * @param article
     */
    @AutoFill(OperationType.UPDATE)
    void updateArticleInfoById(Article article);

    @Delete("delete from article where id =#{id}")
    void deleteArticleById(Integer id);
}
