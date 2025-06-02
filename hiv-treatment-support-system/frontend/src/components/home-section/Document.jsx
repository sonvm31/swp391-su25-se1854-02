import React, { useState, useEffect } from 'react';
import './document.css';

const Document = () => {
  const [documents, setDocuments] = useState([]);
  const [showAll, setShowAll] = useState(false);

  useEffect(() => {
    //fetch('/api/doctors') // có data thì gỡ cmt dòng này
    fetch('/api/documents.json')
      .then((res) => res.json())
      .then((data) => setDocuments(data))
      .catch((err) => console.error('Lỗi tải dữ liệu:', err));
  }, []);

  const visibleDocuments = showAll ? documents : documents.slice(0, 3);

  return (
    <section className="document-section">
      <h2 className="document-title">
        Tài liệu & Blog về <span className="highlight">HIV</span>
      </h2>
      <p className="document-subtitle">
        Khám phá các tài liệu chuyên sâu được biên soạn bởi đội ngũ chuyên gia y tế hàng đầu.
      </p>

      <div className="document-grid">
        {visibleDocuments.map((doc) => (
          <div className="document-card" key={doc.id}>
            <h3 className="doc-title">{doc.title}</h3>
            <p className="document-author">👨‍⚕️ {doc.author}</p>
            <p className="document-snippet">
              {doc.content.length > 100 ? doc.content.slice(0, 100) + '...' : doc.content}
            </p>
            <p className="document-date">📅 {new Date(doc.created_at).toLocaleDateString('vi-VN')}</p>
            <button className="btn-read">📖 Đọc bài viết</button>
          </div>
        ))}
      </div>

      {documents.length > 6 && (
        <div className="view-all-container">
          <button
            className="btn-outline"
            onClick={() => setShowAll(!showAll)}
          >
            {showAll ? 'Thu gọn danh sách' : 'Xem tất cả tài liệu'}
          </button>
        </div>
      )}
    </section>
  );
};

export default Document;
