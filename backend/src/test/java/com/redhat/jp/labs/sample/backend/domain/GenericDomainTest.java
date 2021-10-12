package com.redhat.jp.labs.sample.backend.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class GenericDomainTest {

    class Doamin extends GenericDomain {

        private static final long serialVersionUID = 844842321030649093L;

        Integer id;

        public Doamin() {
        }

        public Doamin(Integer _id) {
            this.id = _id;
        }

        protected Object keyObject() {
            return id;
        }
    }

    @Test
    public void equalsNull() {
        Doamin domain = new Doamin(0);
        assertThat(domain.equals(null)).isFalse();
    }

    @Test
    public void equalsNotGenericDomain() {
        Doamin domain = new Doamin(0);
        Object object = new Object();
        assertThat(domain.equals(object)).isFalse();
    }

    @Test
    public void equalsKeyObjectNull() {
        Doamin domain1 = new Doamin();
        Doamin domain2 = new Doamin(1);
        assertThat(domain1.equals(domain2)).isFalse();
        assertThat(domain2.equals(domain1)).isFalse();
    }

    @Test
    public void equalsNot() {
        Doamin domain1 = new Doamin(0);
        Doamin domain2 = new Doamin(1);
        assertThat(domain1.equals(domain2)).isFalse();
        assertThat(domain2.equals(domain1)).isFalse();
    }

    @Test
    public void equals() {
        Doamin domain1 = new Doamin(1);
        Doamin domain2 = new Doamin(1);
        assertThat(domain1.equals(domain2)).isTrue();
        assertThat(domain2.equals(domain1)).isTrue();
    }

    @Test
    public void hashCodeNullNotEqual() {
        Doamin domain1 = new Doamin();
        Doamin domain2 = new Doamin(1);
        // Assert.assertNotEquals(domain1.hashCode(), domain2.hashCode());
        assertThat(domain1.hashCode()).isNotEqualTo(domain2.hashCode());
    }

    @Test
    public void hashCodeNullEqual() {
        Doamin domain1 = new Doamin();
        Doamin domain2 = new Doamin();
        // Assert.assertEquals(domain1.hashCode(), domain2.hashCode());
        assertThat(domain1.hashCode()).isEqualTo(domain2.hashCode());
    }

    @Test
    public void hashCodeNotEqual() {
        Doamin domain1 = new Doamin(0);
        Doamin domain2 = new Doamin(1);
        // Assert.assertNotEquals(domain1.hashCode(), domain2.hashCode());
        assertThat(domain1.hashCode()).isNotEqualTo(domain2.hashCode());
    }

    @Test
    public void hashCodeEqual() {
        Doamin domain1 = new Doamin(0);
        Doamin domain2 = new Doamin(0);
        // Assert.assertEquals(domain1.hashCode(), domain2.hashCode());
        assertThat(domain1.hashCode()).isEqualTo(domain2.hashCode());
    }

}
