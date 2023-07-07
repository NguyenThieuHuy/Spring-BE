package com.spring.mssql.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.spring.mssql.model.Category;
import com.spring.mssql.repository.CategoryRepository;

public class CategoryControllerTest {

    private CategoryController controller;

    @BeforeEach
    public void setUp() {
        controller = new CategoryController();
    }

    @SuppressWarnings("null")
	@Test
    public void testGetAllCategory() {
        ResponseEntity<List<Category>> response = controller.getAllCategory();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }

    @Test
    public void testGetCategoryById() {
        ResponseEntity<Category> response = controller.getCategoryById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void testCreateCategory() {
        Category category = new Category("Category1");
        ResponseEntity<Category> response = controller.createCategory(category);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(category, response.getBody());
    }

    @Test
    public void testUpdateCategory() {
        Category category = new Category("Category1");
        ResponseEntity<Category> response = controller.updateCategory(1, category);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void testDeleteCategory() {
        ResponseEntity<HttpStatus> response = controller.deleteCategory(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testDeleteAllCategory() {
        ResponseEntity<HttpStatus> response = controller.deleteAllCategory();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    // Mock CategoryRepository implementation
    static class MockCategoryRepository implements CategoryRepository {

        @Override
        public List<Category> findAll() {
            return new ArrayList<>();
        }

        @Override
        public Category findBycategoryid(int categoryid) {
            return null;
        }

        @SuppressWarnings("unchecked")
		@Override
        public Category save(Category category) {
            return category;
        }

        @Override
        public void deleteAll() {
            // Do nothing
        }

		@Override
		public List<Category> findAll(Sort sort) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Category> findAllById(Iterable<Integer> ids) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <S extends Category> List<S> saveAll(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void flush() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public <S extends Category> S saveAndFlush(S entity) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void deleteAllInBatch(Iterable<Category> entities) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteAllByIdInBatch(Iterable<Integer> ids) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteAllInBatch() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Category getOne(Integer id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Category getById(Integer id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <S extends Category> List<S> findAll(Example<S> example) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Page<Category> findAll(Pageable pageable) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Optional<Category> findById(Integer id) {
			// TODO Auto-generated method stub
			return Optional.empty();
		}

		@Override
		public boolean existsById(Integer id) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public long count() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void deleteById(Integer id) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void delete(Category entity) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteAllById(Iterable<? extends Integer> ids) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteAll(Iterable<? extends Category> entities) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public <S extends Category> Optional<S> findOne(Example<S> example) {
			// TODO Auto-generated method stub
			return Optional.empty();
		}

		@Override
		public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <S extends Category> long count(Example<S> example) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public <S extends Category> boolean exists(Example<S> example) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public List<Category> findBycategoryname(String categoryname) {
			// TODO Auto-generated method stub
			return null;
		}
    }
}
