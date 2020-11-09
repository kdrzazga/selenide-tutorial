package org.kd.unittest;

import org.kd.decathlon.Article;

public class ArticleSimpleTest {

    public static void main(String[] args) {
        new ArticleSimpleTest().testConstructor();
    }

    public void testConstructor() {
        String tagContent = "";
        Article article = new Article(tagContent);
        //TODO assertEquals("10", article.getPrice());
    }

    private void assertEquals(String s1, String s2) {
        if (s1 == null)
            throw new AssertionError("s1 null");
        else if (!s1.equals(s2))
            throw new AssertionError("not equals");
    }
}
