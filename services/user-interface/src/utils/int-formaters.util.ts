export const formatInteger = (x: number): string => {
    return Intl.NumberFormat().format(x)
}

export const formatCurrency = (x: number): string => {
    return Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "USD"
    }).format(x)
}
