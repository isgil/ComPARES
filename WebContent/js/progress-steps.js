class ProgressSteps extends HTMLElement {
  constructor() {
    super();
    this.attachShadow({ mode: "open" });
  }

  static get observedAttributes() {
    return ["value", "segments", "label"];
  }

  attributeChangedCallback() {
    this.render();
  }

  connectedCallback() {
    this.render();
  }

  // Color intermedio entre rojo → amarillo → verde
  getIntermediateColor(value, max) {
    const ratio = value / max;

    let r, g;

    if (ratio < 0.5) {
      // Rojo intermedio → Amarillo intermedio
      r = 255;
      g = Math.round(120 + (ratio * 2) * 100); // 120 → 220
    } else {
      // Amarillo intermedio → Verde intermedio
      g = 220;
      r = Math.round(255 - ((ratio - 0.5) * 2) * 135); // 255 → 120
    }

    return `rgb(${r}, ${g}, 120)`;
  }

  render() {
    const segments = Number(this.getAttribute("segments") || 10);
    const value = Number(this.getAttribute("value") || 0);
    const label = this.getAttribute("label");

    const color = this.getIntermediateColor(value, segments);

    const style = `
      <style>
        :host {
          display: block;
          position: relative;
        }

        .label {
          position: absolute;
          left: 50%;
          transform: translateX(-50%);
          font-size: 14px;
          font-weight: bold;
          color: #444;
          pointer-events: none;
        }

        .container {
          display: flex;
          gap: 3px;
          height: 20px;
        }

        .step {
          flex: 1;
          background: #e8e8e8;
          border-radius: 2px;
          transition: background 0.3s ease;
        }

        .step.active {
          background: ${color};
        }
      </style>
    `;

    let stepsHtml = "";
    for (let i = 0; i < segments; i++) {
      stepsHtml += `<div class="step ${i < value ? "active" : ""}"></div>`;
    }

    const labelHtml = label ? `<div class="label">${label}</div>` : "";

    this.shadowRoot.innerHTML = `
      ${style}
      ${labelHtml}
      <div class="container">${stepsHtml}</div>
    `;
  }

  setValue(v) {
    this.setAttribute("value", v);
  }

  setSegments(n) {
    this.setAttribute("segments", n);
  }

  setLabel(text) {
    this.setAttribute("label", text);
  }
}

customElements.define("progress-steps", ProgressSteps);
