package cn.example.zengjjsc.es;


import cn.example.zengjjsc.es.dto.KbsPointEsModel;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TextQueryType;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.GetIndicesSettingsResponse;
import co.elastic.clients.elasticsearch.indices.GetMappingResponse;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

//不使用spring 的 就不加注解了
/*@SpringBootTest(classes = ZengJjScTestBootApplication.class)*/
/*@RunWith(SpringRunner.class)*/
public class ElasticsearchTest implements ElasticsearchClientCtrl {

    /**
     * 获取 配置
     *
     * @throws IOException
     */
    @Test
    public void get_crmkbsnew1_settings() throws IOException {
        ElasticsearchClient client = getElasticsearchClient();
        GetIndicesSettingsResponse settings = client.indices().getSettings(a -> a.index("crmkbsnew1new1"));
        System.out.println("crmkbsnew1 获取配置:" + settings);
    }

    /**
     * 保存
     *
     * @throws IOException
     */
    @Test
    public void save() throws IOException {
        Random random = new Random();
        KbsPointEsModel model = new KbsPointEsModel();
        model.setPointId(String.valueOf(random.nextInt(1000000)));

        model.setTitle("标题 " + model.getPointId());
        model.setOrganId("Organ " + model.getPointId());
        model.setOrganName("OrganName " + model.getPointId());
        model.setCreateCode("CreateCode " + model.getPointId());
        model.setCreateName("CreateName " + model.getPointId());
        model.setCreatetime(new Date());
        model.setDirectoryId("Directory " + model.getPointId());
        model.setContent("Content " + model.getPointId());
        model.setContentHtml("ContentHtml " + model.getPointId());
        model.setPageView(random.nextInt(100));
        model.setPriority("Priority " + model.getPointId());
        model.setKeyWords("KeyWords " + model.getPointId());
        model.setRemark("Remark " + model.getPointId());

        model.setStatus("2 ");
        model.setValidStartDate(new Date());
        model.setValidEndDate(new Date());
        model.setReviewReason("审核意见");


        ElasticsearchClient client = getElasticsearchClient();
        IndexResponse indexResponse = client.index(i -> i
                .index("crmkbsnew1")
                //设置id
                .id(model.getPointId())
                //传入user对象
                .document(model));

        System.out.println("crmkbsnew1 保存 :" + indexResponse);
    }


    /**
     * 根据ID删除
     */
    @Test
    public void deleteById() throws IOException {
        String id = "0001";
        ElasticsearchClient client = getElasticsearchClient();
        DeleteResponse deleteResponse = client.delete(d -> d
                .index("crmkbsnew1")
                .id(id)
        );
        System.out.println("crmkbsnew1 根据ID删除 :" + deleteResponse);
    }


    /**
     * 根据ID判断存在
     */
    @Test
    public void existsById() throws IOException {
        String id = "";
        ElasticsearchClient client = getElasticsearchClient();
        boolean have = true;

        BooleanResponse indexResponse = client.exists(e -> e.index("crmkbsnew1").id(id));
        have = indexResponse.value();

        System.out.println("crmkbsnew1 根据ID判断存在:" + have);
    }

    /**
     * 搜索
     * @throws IOException
     */
    public  void search() throws IOException {
        ElasticsearchClient client = getElasticsearchClient();

        String[] keyWordArr = {"",""};
        BoolQuery.Builder boolQueryBuilder = new BoolQuery.Builder();
        for (String keyword : keyWordArr) {
            boolQueryBuilder.must(m -> m
                    .multiMatch(t -> t
                            .fields("title", "keyWords", "contentHtml")
                            .query(keyword)
                            .type(TextQueryType.Phrase)
                    )
            );
        }

        SearchResponse<KbsPointEsModel> hits = client.search(s -> s
                .index("crmkbs")
                .query(q -> q.bool(boolQueryBuilder.build()))
                .from(0)
                .size(100), KbsPointEsModel.class
        );

        List<KbsPointEsModel> reList =  hits.hits().hits().stream().map(Hit::source).toList();
        System.out.println("crmkbsnew1 搜索 :" + reList);
        
        
    }


    /**
     * 获取  Mappings
     * 
     * 
     * 
     */
    @Test
    public void getMapping() throws IOException {
        ElasticsearchClient client = getElasticsearchClient();
        GetMappingResponse crmkbsnew1 = client.indices().getMapping(e -> e.index("crmkbsnew1"));
        System.out.println("crmkbsnew1 获取  Mappings:" + crmkbsnew1);
    }
    

}
