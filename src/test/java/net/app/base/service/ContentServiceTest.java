package net.app.base.service;

import net.app.base.CrawlerReadFeedApplication;
import net.app.base.model.Content;
import net.app.base.model.UlContent;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = CrawlerReadFeedApplication.class)
@AutoConfigureMockMvc
public class ContentServiceTest {

    @Autowired
    private ContentService contentService;

    @Test
    public void getContentByHtml_htmlEmpty_shouldReturnEmptyList() throws Exception {

        List<Content> contents = contentService.getContentByHtml("");

        Assert.assertEquals(contents.size(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getContentByHtml_htmlNull_shouldReturnError() throws Exception {

        List<Content> contents = contentService.getContentByHtml(null);

        Assert.assertEquals(contents.size(), 0);
    }

    @Test
    public void getContentByHtml_htmlTagP_shouldReturnWith3ContentTypeText() throws Exception {

        String html = "<p>\n" +
                " &nbsp;</p>\n" +
                " <p>\n" +
                " No fim, <strong>o T-Cross deve ter cerca de 4,13 metros de comprimento e 2,56 metros de entre-eixos.</strong> Como acontece com o Polo e <a href=\"http://revistaautoesporte.globo.com/Noticias/noticia/2017/11/volkswagen-virtus-e-apresentado-no-brasil.html\"><strong>sed&atilde; Virtus</strong></a>, tamb&eacute;m constru&iacute;dos sobre a arquitetura MQB A0, <strong>o crossover pequeno ser&aacute; produzido em S&atilde;o Bernardo do Campo, S&atilde;o Paulo.</strong></p>\n" +
                " <p>\n" +
                " &Eacute; bem prov&aacute;vel que ele seja oferecido no mercado brasileiro com os <strong>motores 1.6 16V aspirado de 117 cv e 16,5 kgfm de torque e 1.0 TSI (turbo) de 128 cv e 20,4 kgfm, associados ao c&acirc;mbio manual de cinco marchas e autom&aacute;tico de seis marchas. ]</strong></p>";

        List<Content> contents = contentService.getContentByHtml(html);

        Assert.assertEquals(contents.size(), 3);
        Assert.assertEquals(contents.get(0).getType(), "text");
        Assert.assertEquals(contents.get(1).getType(), "text");
        Assert.assertEquals(contents.get(2).getType(), "text");
    }

    @Test
    public void getContentByHtml_htmlTagImg_shouldReturnWith1ContentTypeImg() throws Exception {

        String html = "<div class=\"foto componente_materia midia-largura-620\">\n" +
                " <img alt=\"Volkswagen T-Cross é fotogrado em testes na Finlândia (Foto: Automedia)" +
                "\" height=\"413\" id=\"256547\" " +
                "src=\"http://s2.glbimg.com/Tmltwt5wjfia6qTzJxIsH2ZIvrE=/620x413/e.glbimg.com/og/ed/f/original/2017/12/14/vw-t-cross-004.jpg\" " +
                "title=\"Volkswagen T-Cross é fotogrado em testes na Finlândia (Foto: Automedia)\" width=\"620\" /><label " +
                "class=\"foto-legenda\">Volkswagen T-Cross &eacute; fotogrado em testes na Finl&acirc;ndia (Foto: Automedia)</label></div>";

        List<Content> contents = contentService.getContentByHtml(html);

        Assert.assertEquals(contents.size(), 1);
        Assert.assertEquals(contents.get(0).getType(), "image");
    }

    @Test
    public void getContentByHtml_htmlTagUl_shouldReturnWith1ContentTypeLinks() throws Exception {

        String html = "<div class=\"saibamais componente_materia expandido\">\n" +
                " <strong>saiba mais</strong>\n" +
                " <ul>\n" +
                " <li>\n" +
                " <a href=\"http://revistaautoesporte.globo.com/Noticias/noticia/2017/12/volkswagen-confirma-proximos-lancamento-incluindo-polo-gts-e-o-sucessor-do-gol.html" +
                "\">VOLKSWAGEN CONFIRMA PR&Oacute;XIMOS LAN&Ccedil;AMENTO, INCLUINDO POLO GTS E O SUCESSOR DO GOL</a></li>\n" +
                " <li>\n" +
                " <a href=\"http://revistaautoesporte.globo.com/Noticias/noticia/2017/11/volkswagen-confirma-t-cross-para-o-segundo-semestre-de-2018.html" +
                "\">VOLKSWAGEN CONFIRMA T-CROSS PARA O SEGUNDO SEMESTRE DE 2018</a></li>\n" +
                " </ul>\n" +
                " </div>";

        List<Content> contents = contentService.getContentByHtml(html);

        Assert.assertEquals(contents.size(), 1);
        Assert.assertEquals(contents.get(0).getType(), "links");
        Assert.assertEquals(((UlContent) contents.get(0)).getContent().get(0), "http://revistaautoesporte.globo.com/Noticias/noticia/2017/12/volkswagen-confirma-proximos-lancamento-incluindo-polo-gts-e-o-sucessor-do-gol.html");
        Assert.assertEquals(((UlContent) contents.get(0)).getContent().get(1), "http://revistaautoesporte.globo.com/Noticias/noticia/2017/11/volkswagen-confirma-t-cross-para-o-segundo-semestre-de-2018.html");
    }

}
