import React from 'react';
import { Link } from 'react-router-dom';

function Navbar() {
  return (
    <header className="d-flex flex-wrap align-items-center justify-content-between py-3 mb-4 border-bottom px-3">
      {/* Logo */}
      <div className="col-md-2 mb-2 mb-md-0">
        <Link to="/" className="d-inline-flex link-body-emphasis text-decoration-none">
          <svg className="bi" width="40" height="32">
            <use xlinkHref="#bootstrap"></use>
          </svg>
        </Link>
      </div>

      {/* Menu */}
      <ul className="nav col-md-4 justify-content-center mb-md-0">
        <li><Link to="/" className="nav-link px-2 link-secondary">Home</Link></li>
        <li><Link to="" className="nav-link px-2">Features</Link></li>
        <li><Link to="" className="nav-link px-2">Pricing</Link></li>
        <li><Link to="" className="nav-link px-2">FAQs</Link></li>
        <li><Link to="" className="nav-link px-2">About</Link></li>
      </ul>

      {/* Search bar */}
      <form className="col-md-3 mb-2 mb-md-0">
        <input
          type="search"
          className="form-control"
          placeholder="Search..."
          aria-label="Search"
        />
      </form>

      {/* Login/Signup buttons */}
      <div className="col-md-3 text-end">
        <Link to="/login" className="btn btn-outline-primary me-2">Login</Link>
        <Link to="/register" className="btn btn-primary">Sign-up</Link>
      </div>
    </header>
  );
}

export default Navbar;
