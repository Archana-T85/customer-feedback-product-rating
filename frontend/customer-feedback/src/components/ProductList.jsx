import { useEffect, useState } from "react";
import API from "../services/api";

function ProductList({ setSelectedProduct }) {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = () => {
    API.get("/products")
      .then((response) => {
        setProducts(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div className="product-section">
      <h2>Products</h2>

      <div className="product-grid">
        {products.map((product) => (
          <div className="product-card" key={product.productId}>
            <img
              src={product.imageUrl}
              alt={product.productName}
              className="product-image"
            />

            <h3>{product.productName}</h3>

            <p>{product.description}</p>

            <h4>₹ {product.price}</h4>

            <button onClick={() => setSelectedProduct(product)}>
              Select
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default ProductList;