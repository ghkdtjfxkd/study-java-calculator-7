package calculator.domain.Calculation;

public enum Operation {
    PLUS() {
        public Long apply(Long x, Long y) { return x + y; }
    };

    public abstract Long apply(Long x, Long y);
}